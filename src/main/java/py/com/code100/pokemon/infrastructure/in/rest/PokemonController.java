package py.com.code100.pokemon.infrastructure.in.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

    // URLS
    private static final String URL_GET_ALL = "";
    private static final String URL_ID = "{id}";
    private static final String URL_POST_CREATED = "";

    // COMMANDS AND QUERYS
    private final PaginatedPokemonQuery pokemenQuery;
    private final FindPokemonQuery findPokemonQuery;
    private final CreatedPokemonCommand createdPokemonCommand;
    private final UpdatedPokemonCommand updatedPokemonCommand;
    private final DeletedPokemonCommand deletedPokemonCommand;


    @GetMapping(URL_GET_ALL)
    public RestResponse<PaginationResponse<PokemonResponseModel>> list(
            @RequestParam(value = "filters", required = false) List<String> filters,
            @RequestParam(value = "orders", required = false) List<String> orders,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "${config.default-paginated}") Integer pageSize
    ) {
        var response = pokemenQuery.execute(filters, orders, page, pageSize);

        return RestResponse.<PaginationResponse<PokemonResponseModel>>builder()
                .status(HttpStatus.OK.value())
                .data(PaginationResponse.<PokemonResponseModel>builder()
                        .page(response.getPage())
                        .size(response.getSize())
                        .total(response.getTotal())
                        .data(response.getData().stream().map(PokemonResponseModel::of).collect(Collectors.toList()))
                        .build())
                .build();
    }

    @GetMapping(URL_ID)
    public RestResponse<PokemonResponseModel> getId(
            @Param("id") String id
    ) {
        var response = findPokemonQuery.execute(UUID.fromString(id));

        return RestResponse.<PokemonResponseModel>builder()
                .status(HttpStatus.OK.value())
                .data(PokemonResponseModel.of(response))
                .build();
    }

    @PostMapping(URL_POST_CREATED)
    @ResponseStatus(HttpStatus.CREATED)
    public void created(@Valid @RequestBody PokemonRequestModel request) {
        createdPokemonCommand.execute(request.toDomain());
    }

    @PutMapping(URL_ID)
    public RestResponse<PokemonResponseModel> updated(
            @Param("id") String id,
            @Valid @RequestBody PokemonRequestModel request
    ) {
        var response = updatedPokemonCommand.execute(UUID.fromString(id), request.toDomain());

        return RestResponse.<PokemonResponseModel>builder()
                .status(HttpStatus.OK.value())
                .data(PokemonResponseModel.of(response))
                .build();
    }

    @DeleteMapping(URL_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleted(@Param("id") String id) {
        deletedPokemonCommand.execute(UUID.fromString(id));
    }
}
