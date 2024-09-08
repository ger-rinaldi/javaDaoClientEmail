package org.Enviroment;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvSingleton {
    private static Dotenv dotenv = null;

    public static Dotenv getDotenv() {
        if (dotenv == null) {
            dotenv = Dotenv.configure().load();
        }
        return dotenv;
    }
}
