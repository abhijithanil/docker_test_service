# Docker test service

### Requirements
- Docker [pre installed]

### Service deployment
- `docker build -t <>/docker-test-service .` //creating an image
- `docker run -p 8080:8080 <image-id>`   //running the image

### Using docker-compose
- `docker-compose up --build`  (-d) flag if console not required
- `docker build . -f Dockerfile.dev` // build dev

### Using ngnix
- `cd ngnix`
- `docker-compose up --build`  (-d) flag if console not required

### Ref
https://www.youtube.com/watch?v=IGg1Rx29_O0&ab_channel=Codeching


### APIS
- GET
  `http://localhost:8080/visits`
- POST
  `http://localhost:8080/visits`
   ``` 
     {
       "userId": "12"
     }
    ```
