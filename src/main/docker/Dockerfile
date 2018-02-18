FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD tweetsearch-0.0.1.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENV SPRING_SOCIAL_TWITTER_APPID=""
ENV SPRING_SOCIAL_TWITTER_APPSECRET=""
ENV TWITTER_ACCESS_TOKEN=""
ENV TWITTER_ACCESS_TOKEN_SECRET=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.social.twitter.appid=${SPRING_SOCIAL_TWITTER_APPID} -Dspring.social.twitter.appsecret=${SPRING_SOCIAL_TWITTER_APPSECRET} -Dtwitter.access.token=${TWITTER_ACCESS_TOKEN} -Dtwitter.access.token.secret=${TWITTER_ACCESS_TOKEN_SECRET} -jar /app.jar " ]
