package com.github.austenwade.maven.plugins.deno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "run-tasks", defaultPhase = LifecyclePhase.COMPILE)
public class DenoTasksMojo extends AbstractMojo {

    @Parameter(property = "project", readonly = true)
    private MavenProject project;

    @Parameter(property = "tasks", defaultValue = "dist")
    private String[] tasks;

    Log log;

    public void execute() throws MojoExecutionException, MojoFailureException {
        log = getLog();

        for (String task : tasks) {
            String[] command = {"deno", "task", task};
            ProcessBuilder processBuilder = new ProcessBuilder(command);
    
            try {
                Process process = processBuilder.start();
    
                InputStream inputStream = process.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                log.info("\n");
                log.info("\nRunning Deno Task");
                while ((line = bufferedReader.readLine()) != null) {
                    log.info(line);
                }
                log.info("\n");
                int exitCode = process.waitFor();
                log.info("Process exited with code: " + exitCode);
    
                log.info("Deno run completed with exit code" + exitCode);
            } catch (IOException e) {
                log.error("IOException: " + e.getMessage());
            } catch(InterruptedException e) {
                log.error("InterruptedException: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

    }
}
