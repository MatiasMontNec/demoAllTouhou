-- Insertar personajes
INSERT INTO character (id, name, age, gender, height, weight, biography, lives_in, group_species)
VALUES
    (1, 'Reimu Hakurei PC98',
     17,
     'Female',
     155,
     47,
     'Reimu Hakurei es la miko y sacerdotisa del Templo Hakurei y la personaje principal de Touhou Project' ||
     ' junto con Marisa Kirisame. Fue el primer personaje creado por ZUN, y a lo largo de sus apariciones ha sufrido' ||
     ' algunos cambios, tanto físicos como mentales.',
     'Templo Hakurei',
     3);

-- Insertar datos de imágenes relacionadas con Reimu Hakurei
INSERT INTO images (id, name, image)
VALUES
    (1, 'first', '...'),
    (2, 'second', '...'),
    (3, 'third', '...'),
    (4, 'love', '...'),
    (5, 'hate', '...'); -- Asegúrate de sustituir '...' por los datos binarios de la imagen.

-- Relacionar Reimu Hakurei con sus imágenes
INSERT INTO images_character (images_id, character_id)
VALUES
    (1, 1),
    (2, 1);

-- Insertar poderes relacionados con Reimu Hakurei
INSERT INTO powers (id, name, description)
VALUES
    (1, 'Fantasy Seal',
     'Un poder que permite a Reimu conjurar un sello que derrota a los enemigos');

-- Relacionar Reimu Hakurei con sus poderes
INSERT INTO powers_character (powers_id, character_id)
VALUES
    (1, 1);

-- Insertar especie relacionada con Reimu Hakurei
INSERT INTO species (id, name, description, type)
VALUES
    (1, 'Human',
     'Ser humano común en Gensokyo',
     'Humano');

-- Relacionar Reimu Hakurei con su especie
INSERT INTO species_character (species_id, character_id)
VALUES
    (1, 1);

-- Insertar gustos de Reimu Hakurei
INSERT INTO likes (id, name, description)
VALUES
    (1, 'Sake',
     'Reimu disfruta tomar sake cuando está relajada');

-- Relacionar Reimu con sus gustos
INSERT INTO likes_character (likes_id, character_id)
VALUES
    (1, 1);

-- Insertar disgustos de Reimu Hakurei
INSERT INTO dislikes (id, name, description)
VALUES
    (1, 'Ser molestada',
     'Reimu no soporta que la interrumpan mientras trabaja en su templo');

-- Relacionar Reimu con sus disgustos
INSERT INTO character_dislikes (dislike_id, character_id)
VALUES
    (1, 1);

-- Insertar relaciones entre personajes
-- Relación entre Reimu Hakurei y Marisa Kirisame (Tipo de relación: 'Amistad')
INSERT INTO character_relation (character_id, related_character_id, type_relation)
VALUES
    (1, (SELECT id FROM character WHERE name = 'Marisa Kirisame'), 'Amigas y buenas rivales');
