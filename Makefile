javac -classpath ./classes -sourcepath ./src compil: Flow.class

Flow.class: src/fordfuklerson/.java
    javac --source-path ./src --class-path ./classes:lib/ -d ./classes ./src/fordfuklerson/Flow.java

run: classes/fordfuklerson/Flow.class
    java -classpath ./classes/:./lib/* fordfuklerson.Flow

clean:
    rm -rf classes/

all : 