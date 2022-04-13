mkdir -p build
echo "Compiling..."
javac -d build java/*.java || exit 1
echo "Running test..."
java -classpath build RateLimiterTest || exit 1