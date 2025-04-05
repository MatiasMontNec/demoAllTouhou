-- Insertar el personaje principal
INSERT INTO character (id, name, age, gender, height, weight, biography, lives_in, group_species,link1, link2,
                       image_path1, image_path2, image_path3, image_path4, image_path5) VALUES (
     1,
     'Reimu Hakurei (PC98)',
     10,
     'Femenino',
     155,
     47,
     'Es la miko y sacerdotisa del templo hakurei y la personaje principal de touhou project junto con Marisa ' ||
     'Kirisame. Fue el primer personaje creado por ZUN, y a lo largo de sus apariciones ha sufrido algunos cambios,' ||
     ' tanto físicos como mentales.',
     'Templo Hakurei',
     1, -- Humana
     'https://touhou.fandom.com/es/wiki/Reimu_Hakurei',
     'https://es.touhouwiki.net/wiki/Reimu_Hakurei',
     '/images/characters/Reimu_Hakurei/fa1.png',
     '/images/characters/Reimu_Hakurei/fa2.png',
     '/images/characters/Reimu_Hakurei/original.png',
     '/images/characters/Reimu_Hakurei/love.png',
     '/images/characters/Reimu_Hakurei/angry.png'
 );

-- Insertar a Marisa Kirisame (versión Windows) con sus imágenes
INSERT INTO character (
    id, name, age, gender, height, weight,
    biography, lives_in, group_species,
    link1, link2,
    image_path1, image_path2, image_path3, image_path4, image_path5
) VALUES (
     2,  -- Nuevo ID para diferenciar de la versión PC-98
     'Marisa Kirisame',
     NULL,  -- Edad desconocida (aproximadamente la misma que Reimu)
     'Femenino',
     NULL,  -- height no especificado
     NULL,  -- weight no especificado
     'Marisa es una maga humana famosa por su habilidad con la magia y por ser una ladrona y aventurera. ' ||
     'Vive en el Bosque Mágico, y es conocida por su relación cercana con Reimu Hakurei. Ha sido tanto ' ||
     'amiga como rival de varios personajes, y tiene un carácter impulsivo y entusiasta.',
     'Bosque Mágico',
     1,  -- Humana
     'https://touhou.fandom.com/es/wiki/Marisa_Kirisame',
     'https://es.touhouwiki.net/wiki/Marisa_Kirisame',
     '/images/characters/Marisa_Kirisame/windows_fa1.png',
     '/images/characters/Marisa_Kirisame/windows_fa2.png',
     '/images/characters/Marisa_Kirisame/windows_original.png',
     '/images/characters/Marisa_Kirisame/windows_love.png',
     '/images/characters/Marisa_Kirisame/windows_angry.png'
);

-- Actualizar la secuencia para el próximo ID
SELECT setval('character_id_seq', (SELECT MAX(id) FROM character));