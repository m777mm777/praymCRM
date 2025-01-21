FROM openjdk:17 as build
COPY target/*.jar app.jar
COPY target/classes/static/images /app/resources/static/images

FROM nginx:stable-alpine
# COPY --from=build /dist /usr/share/nginx/html
COPY --from=build praymCRM/praymgroupp/nginx.conf /etc/nginx.conf.d/default.conf
EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]

ENTRYPOINT ["java","-jar","/app.jar"]