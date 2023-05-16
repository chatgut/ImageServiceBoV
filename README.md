# Spring Boot Application - Image Microservice

This is a microservice that handles images. It can receive and upload them.

Runs on port 8001.

## Getting Started





## Endpoints 
These are the available endpoints: 

### POST /images
Request parameters: 
- `image` - The image file to upload.

### GET /images/{id}
- `url - http://localhost:8001/images/1` 

Returns the image.

### DELETE /images/{id}
- `url - http://localhost:8001/images/1` 

Deletes the image.


## Responses
#### `POST localhost:8080/images`
- `200 OK` if image is uploaded
- `400 BAD REQUEST` if image is not uploaded

#### `GET localhost:8080/images/{id}`
- `200 OK` if there is a image to return
- `404 NOT FOUND`if there is no matching image

#### `DELETE localhost:8080/images/{id}`
- `200 OK` always
