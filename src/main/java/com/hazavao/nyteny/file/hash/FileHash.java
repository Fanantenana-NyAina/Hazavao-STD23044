package com.hazavao.nyteny.file.hash;

import com.hazavao.nyteny.PojaGenerated;

@PojaGenerated
public record FileHash(FileHashAlgorithm algorithm, String value) {}
