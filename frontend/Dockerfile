#
# Author: Rico Krenn
# Created: 06/22/2026
# Version: 1.0
# Description: Dockerfile for nginx
# Project: 200_Abschlussprojekt
#

FROM node:20-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80