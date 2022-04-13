mkdir -p build
echo "Compiling..."
javac -d build java/*.java || exit 1
echo "Running Web Server..."
java -classpath build WebServer || exit 1