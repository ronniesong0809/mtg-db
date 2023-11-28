package com.ronsong.mtgdb.common;

public class Constants {
    public enum Colors {
        W("White"), U("Blue"), B("Black"), R("Red"), G("Green");
        private final String name;

        Colors(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
