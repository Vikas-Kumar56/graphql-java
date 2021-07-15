package com.social.graphqlsdl.context.dataloader;

import com.social.graphqlsdl.Service.AuthorService;
import com.social.graphqlsdl.dto.AuthorDto;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class DataLoaderRegistryFactory {

    public static final String AUTHOR_DATA_LOADER = "AUTHOR_DATA_LOADER";
    private static final Executor authorThreadPool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    private final AuthorService authorService;

    public DataLoaderRegistryFactory(AuthorService authorService) {
        this.authorService = authorService;
    }

    public DataLoaderRegistry build() {
        DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();

        dataLoaderRegistry.register(AUTHOR_DATA_LOADER, createAuthorDataLoader());

        return dataLoaderRegistry;
    }

    private DataLoader<UUID, AuthorDto> createAuthorDataLoader() {
        return DataLoader.newMappedDataLoader((Set<UUID> authorIds) -> CompletableFuture.
                        supplyAsync(() -> authorService.getAuthorsByIds(authorIds),
                                authorThreadPool),
                DataLoaderOptions.newOptions().setMaxBatchSize(2));
    }
}
