#!/bin/bash

echo "Building Library Management System..."
mvn clean install

if [ $? -ne 0 ]; then
    echo "Build failed!"
    exit 1
fi

echo ""
echo "Starting Library Management System..."
mvn spring-boot:run

