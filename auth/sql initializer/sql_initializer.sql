--jack -> ssh! it's a secret
--john -> my super secret


INSERT INTO auth.user_credential(
	username, password, is_account_enabled, authority)
	VALUES ('john', '$2a$10$Up8f1x3zp3kIYTDn/krCxODUAdrRCzKX10my2FrieQfaYInJ2UH52', 'true', 'USER');
INSERT INTO auth.user_credential(
	username, password, is_account_enabled, authority)
	VALUES ('jack', '$2a$10$c7y2lXvg.8a0Cg1ROopy2uxZSg1KSpTuyCkBpG2lHim0zoFNoM3mG', 'true', 'ADMIN');	