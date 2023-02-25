# Usage

Can be used to compile targets or run tasks with deno.

Made to familiarize myself with Maven Plugins. Doesn't really do much.


```xml
...
<plugin>
    <groupId>com.github.austenwade</groupId>
    <artifactId>deno-maven-plugin</artifactId>
    <version>1.0-SNAPSHOT</version>
    <configuration>
        <!-- optional, the command parameter can be changed here too -->
        <command>git status</command>
    </configuration>
    <executions>
        <execution>
            <id>Compile Deno Entry</id>
            <goals>
                <goal>compile-src</goal>
            </goals>
            <configuration>
                <entry>compileTarget.ts</entry>
                <outputDir>mvn-out</outputDir>
            </configuration>
        </execution>
        <execution>
            <id>Run Deno Tasks</id>
            <goals>
                <goal>run-tasks</goal>
            </goals>
            <configuration>
                <tasks>
                    <task>run</task>
                    <task>compile</task>
                </tasks>
            </configuration>
        </execution>
    </executions>
</plugin>
...
```