FROM ubuntu:latest
RUN apt update
RUN apt install apache2 -y
COPY index.html /var/www/html/
EXPOSE 80
CMD ["apache2ctl", "-D", "FOREGROUND"]






#FROM ubuntu
#RUN apt update
#RUN apt install default-jdk -y
#COPY . .
#RUN javac Hello.java
#CMD ["java", "Hello"]


#FROM amazoncorretto:latest
#RUN mkdir /app
#COPY Hello.java /app
#WORKDIR /app
#RUN javac Hello.java
#CMD ["java", "Hello"]

