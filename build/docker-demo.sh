
# 先清空
mvn clean -f ../pom.xml
# 先执行common打包
mvn package -f ../common/pom.xml

# 然后在对docker打包 这里依赖common包
mvn package -f ../pom.xml


# 上一级目录
basePath=$(dirname "$PWD")
#docker build -f ProductServiceDockerfile .

# 进入product-service目录
cd $basePath/product-service
# 构建product-service
docker build -f Dockerfile -t dockerRegisterServer:5000/product-service:1.0.0 .

# 进入product-client目录
cd $basePath/product-client
# 构建product-client
docker build -f Dockerfile -t dockerRegisterServer:5000/product-client:1.0.0 .

