

INSERT INTO `colman`.`USER`
(`id`,
`email`,
`password`,
`username`)
VALUES
(1,
"admin@admin.com",
"714150f5dc7093ffb301731205debe6d40c437e200f45c212ec6c70dfc0a33acde7d23642c08bcb5",
"admin"
);

INSERT INTO `colman`.`USER`
(`id`,
`email`,
`password`,
`username`)
VALUES
(
2,
"test@test.com",
"0cdcb5d021f35dba0195ff5de661a95e5a8da630cffa62e56c7ceab13a38fe25f3d2e83f4351f974",
"user"
);


INSERT INTO `colman`.`ROLES`
(`user_id`,
`user_role`)
VALUES
(
1,
"ADMIN"
);
INSERT INTO `colman`.`ROLES`
(`user_id`,
`user_role`)
VALUES
(2,
"USER"
);
