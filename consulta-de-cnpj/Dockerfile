FROM node:16-alpine as build-step
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . ./
RUN npm run build


FROM nginx:alpine
COPY --from=build-step /app/dist/consulta-de-cnpj /usr/share/nginx/html/
