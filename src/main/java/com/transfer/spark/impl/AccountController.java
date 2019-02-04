package com.transfer.spark.impl;

import com.google.inject.Singleton;
import com.transfer.spark.SparkController;

import static spark.Spark.*;

@Singleton
public class AccountController implements SparkController {

    @Override
    public void init() {
        get("/helloAccount", (req, res) -> "Hello World helloAccount");
    }
}
