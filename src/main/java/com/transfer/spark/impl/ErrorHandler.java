package com.transfer.spark.impl;

import com.google.inject.Singleton;
import com.transfer.exception.ApplicationException;
import com.transfer.spark.SparkController;
import com.transfer.spark.util.ErrorDescription;

import static com.transfer.spark.util.JsonUtils.*;
import static spark.Spark.*;

@Singleton
public class ErrorHandler implements SparkController {

    @Override
    public void init() {

        afterAfter((req, res) -> res.type("application/json"));

        notFound(toJsonString(new ErrorDescription(404, "Not found.")));

        internalServerError(toJsonString(new ErrorDescription(500, "Internal server error.")));

        exception(Exception.class, (exception, req, res) -> {
            Exception appException = exception;

            if (exception.getCause() != null) {
                appException = new ApplicationException(exception.getCause());
            }

            res.status(400);
            res.body(toJsonString(new ErrorDescription(400, appException.getMessage(), appException.getMessage())));
        });
    }
}
