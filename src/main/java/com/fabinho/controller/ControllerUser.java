package com.fabinho.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fabinho.model.User;

@RestController
@RequestMapping("/users")
@Api(tags = "Usuarios", description = "Operaciones para interactuar con usuarios")
public class ControllerUser {

    private final String EXTERNAL_API_URL = "https://jsonplaceholder.typicode.com/users";
    private final RestTemplate restTemplate;

    @Autowired
    public ControllerUser(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Obtener todos los usuarios", notes = "Obtiene una lista de todos los usuarios desde la API externa.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = User[].class),
            @ApiResponse(code = 404, message = "No se encontraron usuarios")
    })
    public ResponseEntity<User[]> getUsersFromExternalApi() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(EXTERNAL_API_URL, User[].class);
        User[] users = response.getBody();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Obtener un usuario por ID", notes = "Obtiene un usuario específico desde la API externa mediante su ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 400, message = "ID no válido"),
            @ApiResponse(code = 404, message = "Usuario no encontrado")
    })
    public ResponseEntity<User> getUserByIdFromExternalApi(@PathVariable("id") String id) {
        try {
            Long userId = Long.parseLong(id);
            String url = EXTERNAL_API_URL + "/" + userId;
            ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(response.getBody());
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Crear un nuevo usuario", notes = "Crea un nuevo usuario en la API externa.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuario creado", response = User.class),
            @ApiResponse(code = 400, message = "Solicitud inválida")
    })
    public ResponseEntity<User> createUser(@RequestBody User user) {
        ResponseEntity<User> response = restTemplate.postForEntity(EXTERNAL_API_URL, user, User.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response.getBody());
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Actualizar un usuario", notes = "Actualiza un usuario existente en la API externa mediante su ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuario actualizado", response = User.class),
            @ApiResponse(code = 400, message = "Solicitud inválida"),
            @ApiResponse(code = 404, message = "Usuario no encontrado")
    })
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        String url = EXTERNAL_API_URL + "/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, User.class);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response.getBody());
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Eliminar un usuario", notes = "Elimina un usuario existente en la API externa mediante su ID.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuario eliminado"),
            @ApiResponse(code = 400, message = "ID no válido"),
            @ApiResponse(code = 404, message = "Usuario no encontrado")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        String url = EXTERNAL_API_URL + "/" + id;
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
