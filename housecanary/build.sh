mkdir -p build
echo "Compiling..."
javac -d build java/*.java || exit 1
