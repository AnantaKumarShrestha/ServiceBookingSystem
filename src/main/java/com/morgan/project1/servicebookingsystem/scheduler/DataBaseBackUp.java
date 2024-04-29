package com.morgan.project1.servicebookingsystem.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Configuration
public class DataBaseBackUp {

    @Value("${database.source.host}")
    private String sourceHost;

    @Value("${database.source.port}")
    private String sourcePort;

    @Value("${database.source.username}")
    private String sourceUser;

    @Value("${database.source.password}")
    private String sourcePassword;

    @Value("${database.source.name}")
    private String sourceName;

    @Value("${sqlFile.prefix}")
    private String sqlFilePrefix;

    @Value("${sqlFile.delete.time}")
    private int sqlFileDeleteTime;



   // @Scheduled(cron = "${create.database.dump.file.crone.time}")
    public void createDatabaseDump() throws IOException, InterruptedException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
        String timestamp = dateFormat.format(new Date());
        String dumpFile = String.format(sqlFilePrefix + "_%s.sql", timestamp);

        File fn = new File("src/main/resources/dumps");
        String dumpFilePath = fn.getAbsolutePath() + "/" + dumpFile;

        ProcessBuilder processBuilder = new ProcessBuilder(
                "pg_dump",
                "-U", sourceUser,
                "-d", sourceName,
                "-h", sourceHost,
                "-p", sourcePort,
                "-f", dumpFilePath
        );
        processBuilder.environment().put("PGPASSWORD", sourcePassword);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Database dumped successfully.");
        } else {
            System.err.println("Error dumping database.");
        }

        deleteOldFiles(fn.getAbsolutePath());
    }

    public void deleteOldFiles(String directoryPath) throws IOException {
        long oneWeekAgo = System.currentTimeMillis() - sqlFileDeleteTime;
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles()) {


                if (file.getName().contains(sqlFilePrefix)) {
                    if (file.lastModified() < oneWeekAgo) {
                        if (file.delete()) {
                            System.out.println("Deleted file: " + file.getName());
                        } else {
                            System.err.println("Failed to delete file: " + file.getName());
                        }
                    }
                }
            }
        }

    }

//    @Scheduled(cron = "${update.clone.database.crone.time}")
//    public void updateCloneDatabase() throws IOException, InterruptedException {
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
//        String timestamp = dateFormat.format(new Date());
//        String dumpFile = String.format(sqlFilePrefix + "_%s.sql", timestamp);
//
//        File fn = new File("src/main/resources/dumps");
//        String dumpFilePath = fn.getAbsolutePath() + "/" + dumpFile;
//
//        System.out.println("asas");
//        ProcessBuilder processBuilder = new ProcessBuilder(
//                "psql",
//                "-U", sourceUser,
//                "-d", "clonedatabase",
//                "-h", sourceHost,
//                "-p", sourcePort,
//                "-f", dumpFilePath
//        );
//        processBuilder.environment().put("PGPASSWORD", sourcePassword);
//        processBuilder.redirectErrorStream(true);
//        Process process = processBuilder.start();
//        int exitCode = process.waitFor();
//        if (exitCode == 0) {
//            System.out.println("Import successful");
//        } else {
//            System.out.println("Import failed");
//        }
//    }







}
