1.) Prerequisites:
Docker & Docker Compose

Java 21

Maven 3.9+


2.) How to Start the System(Docker-Compose)
    command: docker compose up -d --build

3.)To log into database(Use this command to Run Queries):
docker exec -it mysql-db mysql -usahdev -psahdev@123 user_management

4.)Admin User Setup
You can either use the predefined admin user or create a new one.
     Option A — Use Predefined Admin User

        Email	admin@wisemonk.com
        Password	admin123
        Roles	ADMIN, USER

     Option B — Create Admin User Manually(If Database is empty(no Admin User))
		Run inside MySQL:
		
	a.)	INSERT INTO user_management.roles (id, name)
		VALUES (UUID_TO_BIN(UUID()), 'ADMIN');

	b.)	INSERT INTO user_management.users (id, username, email, password, enabled)
		VALUES (
		UUID_TO_BIN(UUID()),
		'admin',
		'admin@wisemonk.com',
		'$2a$12$sHtiSZSjPsPtgcjbiuawGuEj/5hE3oLJDM5YTJb.htmc5HoVd6HOi', -- admin123
		true
		);

	c.)	INSERT INTO user_management.user_roles (user_id, role_id)
		VALUES
		(
		(SELECT id FROM user_management.users WHERE email = 'admin@wisemonk.com'),
		(SELECT id FROM user_management.roles WHERE name = 'ADMIN')
		),
		(
		(SELECT id FROM user_management.users WHERE email = 'admin@wisemonk.com'),
		(SELECT id FROM user_management.roles WHERE name = 'USER')
		);

