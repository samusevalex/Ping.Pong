package com.example;

interface Const {
    String SERVER_IP = "localhost";
    int TCP_PORT = 8023;
    long INIT_BALL_COUNTER = 2000000000;
    long MAX_BALL_COUNTER = 2000000010;
    long OVERFLOW_STATUS = -1;
    String OVERFLOW_MESSAGE = "Counter exceeded";
}