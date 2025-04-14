CREATE TABLE IF NOT EXISTS admin_settings (
                                              id INTEGER PRIMARY KEY AUTOINCREMENT,
                                              password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
                                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                                        image VARCHAR NOT NULL,
                                        is_selected BOOLEAN NOT NULL,
                                        is_synced BOOLEAN NOT NULL,
                                        last_updated DATETIME NOT NULL,
                                        name VARCHAR NOT NULL,
                                        price NUMERIC NOT NULL,
                                        quantity NUMERIC NOT NULL,
                                        type VARCHAR NOT NULL,
                                        unit VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS purchases (
                                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                                         product_id INTEGER NOT NULL,
                                         quantity NUMERIC(10,2) NOT NULL,
                                         price NUMERIC(10,2) NOT NULL,
                                         created_at DATETIME NOT NULL,
                                         is_synced BOOLEAN NOT NULL,
                                         FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS reports (
                                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                                       product_id INTEGER NOT NULL,
                                       quantity NUMERIC(10,2) NOT NULL,
                                       created_at TEXT NOT NULL,
                                       is_synced BOOLEAN NOT NULL,
                                       FOREIGN KEY (product_id) REFERENCES products(id)
);