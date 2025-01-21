FROM openjdk:17 as build
WORKDIR '/builddir'
COPY target/*.jar app.jar
COPY target/classes/static/images /app/resources/static/images

FROM nginx:stable-alpine
# COPY --from=build /dist /usr/share/nginx/html
COPY --from=builder /builddir/build /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/nginx.conf /etc/nginx/conf.d
# COPY --from=build praymgroupp/nginx.conf /etc/nginx.conf.d/default.conf
EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]

ENTRYPOINT ["java","-jar","/app.jar"]