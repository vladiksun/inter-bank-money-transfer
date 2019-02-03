package com.transfer.spark.impl;

import com.transfer.spark.SparkController;

import javax.inject.Singleton;
import static spark.Spark.*;

@Singleton
public class AccountController implements SparkController {

    @Override
    public void init() {
        get("/helloAccount", (req, res) -> "Hello World helloAccount");
    }
}
