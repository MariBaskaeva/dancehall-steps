--liquibase formatted sql

--changeset basma:insert-steps
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Bitter Sweet', 'FEMALE',
        (select id from author where name = 'Kayti Insanity'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Naughty or nice', 'FEMALE',
        (select id from author where name = 'Kayti Insanity'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Dancehall vogue', 'FEMALE',
        (select id from author where name = 'Kayti Insanity'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Unfold', 'FEMALE',
        (select id from author where name = 'Kayti Insanity'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Uppercut', 'FEMALE',
        (select id from author where name = 'Smilez TopNotch'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Mush up', 'FEMALE',
        (select id from author where name = 'Smilez TopNotch'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Pully', 'FEMALE',
        (select id from author where name = 'Dancing Rebel'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'A dat that u want', 'FEMALE',
        (select id from author where name = 'Dancing Rebel'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Non stop', 'FEMALE',
        (select id from author where name = 'Dancing Rebel'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Swiss', 'FEMALE',
        (select id from author where name = 'Dancing Rebel'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Still in love', 'FEMALE',
        (select id from author where name = 'Inspire'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Excuse me', 'FEMALE',
        (select id from author where name = 'Nieka OG'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Flossy whine', 'FEMALE',
        (select id from author where name = 'Nieka OG'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Hot flash', 'FEMALE',
        (select id from author where name = 'Nieka OG'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Prada', 'FEMALE', (select id from author where name = 'Wazzi'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Badish', 'FEMALE', (select id from author where name = 'Darkie'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Motion', 'FEMALE',
        (select id from author where name = 'Kayburr'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Strawberry', 'FEMALE',
        (select id from author where name = 'Mama Blazzaz'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Sweet whine', 'FEMALE',
        (select id from author where name = 'Mama Blazzaz'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Attitude', 'FEMALE',
        (select id from author where name = 'Mama Blazzaz'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Copycat', 'FEMALE',
        (select id from author where name = 'Mama Blazzaz'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'O mama', 'FEMALE',
        (select id from author where name = 'Mama Blazzaz'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Aquatic', 'FEMALE',
        (select id from author where name = 'Mama Blazzaz'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Bounce&Clap', 'FEMALE',
        (select id from author where name = 'Mama Blazzaz'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Affi touch', 'FEMALE',
        (select id from author where name = 'Mama Blazzaz'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Gyal wine', 'FEMALE',
        (select id from author where name = 'Reda TopNotch'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Versatile', 'FEMALE',
        (select id from author where name = 'Latonya style'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Original rude gyal', 'FEMALE',
        (select id from author where name = 'Latonya style'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Nuff respect', 'FEMALE',
        (select id from author where name = 'Latonya style'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Mush up', 'FEMALE',
        (select id from author where name = 'Latonya style'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Back&Forth', 'FEMALE',
        (select id from author where name = 'Dancing Rebel'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Couple up (juk)', 'FEMALE', NULL, 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Sexy body gyal', 'FEMALE',
        (select id from author where name = 'Latonya style'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Hardcore', 'FEMALE',
        (select id from author where name = 'Latonya style'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Above average', 'FEMALE',
        (select id from author where name = 'Latonya style'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Badaboom', 'FEMALE',
        (select id from author where name = 'Kimiko Versatile'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Sweet like sugar', 'FEMALE',
        (select id from author where name = 'Kimiko Versatile'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Cocky broka', 'FEMALE',
        (select id from author where name = 'Kimiko Versatile'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Wind speed', 'FEMALE',
        (select id from author where name = 'Inspire'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Immortality', 'FEMALE',
        (select id from author where name = 'Baby Girl'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Outshine', 'FEMALE',
        (select id from author where name = 'Outshine Team'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Perfect', 'FEMALE',
        (select id from author where name = 'Kissy Mckoy'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Shapeylous', 'FEMALE',
        (select id from author where name = 'Kayti Insanity'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Chrome whine', 'FEMALE',
        (select id from author where name = 'Nikki Chromaz'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Tilt and whine', 'FEMALE', NULL, 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Skettle', 'FEMALE', NULL, 'OLD');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Dutty whine', 'FEMALE', NULL, 'MIDDLE');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Big&Nasty', 'FEMALE',
        (select id from author where name = 'Xpressionz'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Go go whine', 'FEMALE', NULL, 'OLD');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Hot wuk', 'FEMALE', NULL, 'MIDDLE');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Hula hoop', 'FEMALE', NULL, 'MIDDLE');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Steady wine', 'FEMALE', NULL, 'MIDDLE');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Tempa wine', 'FEMALE',
        (select id from author where name = 'Kartoon'), 'MIDDLE');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Tic Toc', 'FEMALE', NULL, 'MIDDLE');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Walk&Shake', 'FEMALE', NULL, 'MIDDLE');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Butterfly', 'FEMALE', NULL, 'OLD');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Slide and wine', 'FEMALE', NULL, 'OLD');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Wata pumpie', 'FEMALE', NULL, 'OLD');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Juk it', 'FEMALE',
        (select id from author where name = 'Xpressionz'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Naked', 'FEMALE',
        (select id from author where name = 'Xpressionz'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'One knock', 'FEMALE',
        (select id from author where name = 'Xpressionz'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Good body', 'FEMALE',
        (select id from author where name = 'Xpressionz'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Tender touch', 'FEMALE',
        (select id from author where name = 'Xpressionz'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Clap Yuself', 'FEMALE',
        (select id from author where name = 'Latonya style'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Jiggle it', 'FEMALE',
        (select id from author where name = 'Latonya style'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Pretty wine', 'FEMALE',
        (select id from author where name = 'Latonya style'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Dig it', 'FEMALE',
        (select id from author where name = 'Kimiko Versatile'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Jafrican whine', 'FEMALE',
        (select id from author where name = 'Kimiko Versatile'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Fabulus', 'FEMALE',
        (select id from author where name = 'Stacya Fia'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'I-bless', 'FEMALE',
        (select id from author where name = 'Stacya Fia'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Ketch a fire', 'FEMALE',
        (select id from author where name = 'Kim Weezy'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Weezy wine', 'FEMALE',
        (select id from author where name = 'Kim Weezy'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Close up', 'FEMALE',
        (select id from author where name = 'Dancing Rebel'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Pon di spot', 'FEMALE',
        (select id from author where name = 'Dancing Rebel'), 'EARLY_NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'XO motion', 'FEMALE',
        (select id from author where name = 'Sara Bendii'), 'NEW');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Happy jook', 'FEMALE',
        (select id from author where name = 'Happyfeet'), 'UNKNOWN');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Badumboom', 'FEMALE',
        (select id from author where name = 'Xqlusiv'), 'UNKNOWN');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Cursive', 'FEMALE',
        (select id from author where name = 'Team Cautiion'), 'UNKNOWN');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Sneak Peak', 'FEMALE',
        (select id from author where name = 'Team Cautiion'), 'UNKNOWN');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Lowkey', 'FEMALE',
        (select id from author where name = 'Ultimate Girlz'), 'UNKNOWN');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Hanky panky', 'FEMALE',
        (select id from author where name = 'Barbie Chelsea'), 'UNKNOWN');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Shorty Infinity', 'FEMALE',
        (select id from author where name = 'Shorty Dancershine'), 'UNKNOWN');
insert into step(id, name, type, author_id, era)
values (nextval('step_sequence'), 'Hurricane Bendii', 'FEMALE',
        (select id from author where name = 'Sara Bendii'), 'UNKNOWN');
