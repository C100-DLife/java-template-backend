package py.com.code100.pokemon.infrastructure.in.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import py.com.code100.core.annotations.WebAdapter;
import py.com.code100.core.models.PaginationResponse;
import py.com.code100.pokemon.application.command.CreatedPokemonCommand;
import py.com.code100.pokemon.application.command.DeletedPokemonCommand;
import py.com.code100.pokemon.application.command.UpdatedPokemonCommand;
import py.com.code100.pokemon.application.query.FindPokemonQuery;
import py.com.code100.pokemon.application.query.PaginatedPokemonQuery;
import py.com.code100.pokemon.infrastructure.in.rest.model.PokemonRequestModel;
import py.com.code100.pokemon.infrastructure.in.rest.model.PokemonResponseModel;
import py.com.code100.pokemon.infrastructure.in.rest.model.RestResponse;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/pokemon")
public class PokemonController {

    private final PaginatedPokemonQuery pokemenQuery;
    private final FindPokemonQuery findPokemonQuery;
    private final CreatedPokemonCommand createdPokemonCommand;
    private final UpdatedPokemonCommand updatedPokemonCommand;
    private final DeletedPokemonCommand deletedPokemonCommand;

    @Operation(summary = "Listado paginado de pokemon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PaginationResponse.class))})
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public RestResponse<PaginationResponse<PokemonResponseModel>> list(
            @RequestParam(value = "filters", required = false) List<String> filters,
            @RequestParam(value = "orders", required = false) List<String> orders,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "${config.default-paginated}") Integer pageSize
    ) {
        var response = pokemenQuery.execute(filters, orders, page, pageSize);

        return RestResponse.<PaginationResponse<PokemonResponseModel>>builder()
                .status(HttpStatus.OK.value())
                .result(PaginationResponse.<PokemonResponseModel>builder()
                        .page(response.getPage())
                        .size(response.getSize())
                        .total(response.getTotal())
                        .data(response.getData().stream().map(PokemonResponseModel::of).collect(Collectors.toList()))
                        .build())
                .build();
    }

    @Operation(summary = "Información de pokemon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PokemonResponseModel.class))})
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public RestResponse<PokemonResponseModel> getById(@PathVariable("id") String id) {
        var response = findPokemonQuery.execute(UUID.fromString(id));

        return RestResponse.<PokemonResponseModel>builder()
                .status(HttpStatus.OK.value())
                .result(PokemonResponseModel.of(response))
                .build();
    }

    @Operation(summary = "Creación de pokemon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ok", content = {@Content(mediaType = "application/json")})
    })
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void created(@Valid @RequestBody PokemonRequestModel request) {
        createdPokemonCommand.execute(request.toDomain());
    }

    @Operation(summary = "Modificar información de pokemon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PokemonResponseModel.class))})
    })
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public RestResponse<PokemonResponseModel> updated(@PathVariable("id") String id,
                                                      @Valid @RequestBody PokemonRequestModel request) {
        var response = updatedPokemonCommand.execute(UUID.fromString(id), request.toDomain());

        return RestResponse.<PokemonResponseModel>builder()
                .status(HttpStatus.OK.value())
                .result(PokemonResponseModel.of(response))
                .build();
    }

    @Operation(summary = "Eliminar información de pokemon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ok", content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleted(@PathVariable("id") String id) {
        deletedPokemonCommand.execute(UUID.fromString(id));
    }
}
