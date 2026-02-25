--liquibase formatted sql

--changeset basma:insert-steps-tags
-- Swiss
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Swiss' and tag.name = 'smooth';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Swiss' and tag.name = 'гармошка';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Swiss' and tag.name = 'кокетливость';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Swiss' and tag.name = 'кулаки';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Swiss' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Swiss' and tag.name = 'наклон';
-- Bitter Sweet
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Bitter Sweet' and tag.name = 'за партой';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Bitter Sweet' and tag.name = 'молитва';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Bitter Sweet' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Bitter Sweet' and tag.name = 'резкость';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Bitter Sweet' and tag.name = 'руки_в_стороны';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Bitter Sweet' and tag.name = 'хлопки';

-- Naughty or nice
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Naughty or nice' and tag.name = 'змейка';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Naughty or nice' and tag.name = 'smooth';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Naughty or nice' and tag.name = 'кокетливость';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Naughty or nice' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Naughty or nice' and tag.name = 'ойлинг';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Naughty or nice' and tag.name = 'поворот';

-- Dancehall vogue
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Dancehall vogue' and tag.name = 'на_грудь';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Dancehall vogue' and tag.name = 'гармошка';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Dancehall vogue' and tag.name = 'кокетливость';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Dancehall vogue' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Dancehall vogue' and tag.name = 'ойлинг';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Dancehall vogue' and tag.name = 'резкость';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Dancehall vogue' and tag.name = 'шаги';

-- Unfold
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Unfold' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Unfold' and tag.name = 'нейтрально';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Unfold' and tag.name = 'подбивка';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Unfold' and tag.name = 'сидонг';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Unfold' and tag.name = 'шейк';

-- Uppercut
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Uppercut' and tag.name = 'cock';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Uppercut' and tag.name = 'juk';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Uppercut' and tag.name = 'агрессивность';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Uppercut' and tag.name = 'кулаки';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Uppercut' and tag.name = 'перестановка_ног';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Uppercut' and tag.name = 'резкость';

-- Mush up (Smilez TopNotch)
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Mush up' and tag.name = 'кулаки';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Mush up' and tag.name = 'нейтрально';

-- Pully
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Pully' and tag.name = 'на_грудь';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Pully' and tag.name = 'нет';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Pully' and tag.name = 'кулаки';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Pully' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Pully' and tag.name = 'пулл';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Pully' and tag.name = 'резкость';

-- A dat that u want
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'A dat that u want' and tag.name = 'волны';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'A dat that u want' and tag.name = 'шлепок';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'A dat that u want' and tag.name = 'juk';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'A dat that u want' and tag.name = 'smooth';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'A dat that u want' and tag.name = 'versatile';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'A dat that u want' and tag.name = 'кулаки';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'A dat that u want' and tag.name = 'ладони';

-- Non stop
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Non stop' and tag.name = 'агрессивность';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Non stop' and tag.name = 'кулаки';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Non stop' and tag.name = 'резкость';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Non stop' and tag.name = 'хлопки';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Non stop' and tag.name = 'шейк';

-- Still in love
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Still in love' and tag.name = 'dutty_wine';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Still in love' and tag.name = 'smooth';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Still in love' and tag.name = 'кокетливость';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Still in love' and tag.name = 'кулаки';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Still in love' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Still in love' and tag.name = 'пулл';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Still in love' and tag.name = 'руки_в_стороны';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Still in love' and tag.name = 'хлопки';

-- Excuse me
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Excuse me' and tag.name = 'шлепок';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Excuse me' and tag.name = 'агрессивность';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Excuse me' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Excuse me' and tag.name = 'резкость';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Excuse me' and tag.name = 'уверенность';

-- Flossy whine
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Flossy whine' and tag.name = 'змейка';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Flossy whine' and tag.name = 'smooth';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Flossy whine' and tag.name = 'кокетливость';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Flossy whine' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Flossy whine' and tag.name = 'ойлинг';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Flossy whine' and tag.name = 'падебуре';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Flossy whine' and tag.name = 'резкость';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Flossy whine' and tag.name = 'уверенность';

-- Hot flash
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Hot flash' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Hot flash' and tag.name = 'резкость';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Hot flash' and tag.name = 'тачинг';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Hot flash' and tag.name = 'уверенность';

-- Prada
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Prada' and tag.name = 'smooth';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Prada' and tag.name = 'кулаки';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Prada' and tag.name = 'ойлинг';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Prada' and tag.name = 'поворот';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Prada' and tag.name = 'шаги';

-- Badish
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Badish' and tag.name = 'smooth';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Badish' and tag.name = 'гармошка';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Badish' and tag.name = 'кокетливость';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Badish' and tag.name = 'кошачьи_лапки';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Badish' and tag.name = 'кулаки';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Badish' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Badish' and tag.name = 'руки_в_стороны';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Badish' and tag.name = 'смена_уровней';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Badish' and tag.name = 'уверенность';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Badish' and tag.name = 'хлопки';

-- Motion
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Motion' and tag.name = 'агрессивность';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Motion' and tag.name = 'кулаки';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Motion' and tag.name = 'перепрыжка';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Motion' and tag.name = 'резкость';

-- Back&Forth
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Back&Forth' and tag.name = 'кулаки';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Back&Forth' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Back&Forth' and tag.name = 'нейтрально';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Back&Forth' and tag.name = 'поворот';

-- Close up
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Close up' and tag.name = 'молчи';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Close up' and tag.name = 'кулаки';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Close up' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Close up' and tag.name = 'нейтрально';

-- Pon di spot
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Pon di spot' and tag.name = 'гармошка';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Pon di spot' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Pon di spot' and tag.name = 'нейтрально';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Pon di spot' and tag.name = 'поворот';

-- Outshine
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Outshine' and tag.name = 'smooth';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Outshine' and tag.name = 'кокетливость';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Outshine' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Outshine' and tag.name = 'тачинг';

-- Shapeylous
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Shapeylous' and tag.name = 'smooth';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Shapeylous' and tag.name = 'ладони';
insert into step_tags(step_id, tag_id) select step.id, tag.id from step, tag where step.name = 'Shapeylous' and tag.name = 'ойлинг';