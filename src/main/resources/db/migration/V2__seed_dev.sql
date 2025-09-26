INSERT INTO category (name, description)
VALUES
  ('Electronics', 'Devices and gadgets'),
  ('Books', 'Books and literature')
ON CONFLICT (name) DO NOTHING;

INSERT INTO product (name, description, price, sku, category_id)
SELECT 'USB-C Cable', '1m charging cable', 19.99::numeric, 'SKU-USB-C-1M', c.id
FROM category c
WHERE c.name = 'Electronics'
ON CONFLICT (sku) DO NOTHING;

INSERT INTO product (name, description, price, sku, category_id)
SELECT 'Clean Code', 'Robert C. Martin', 129.00::numeric, 'SKU-BOOK-CC', c.id
FROM category c
WHERE c.name = 'Books'
ON CONFLICT (sku) DO NOTHING;