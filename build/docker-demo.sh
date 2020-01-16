
# 先执行common打包
mvn package  -f ../common/pom.xml

# 然后在对docker打包 这里依赖common包
mvn package  -f ../pom.xml


docker build -f ProductServiceDockerfile .
docker build -f ProductClientDockerfile .