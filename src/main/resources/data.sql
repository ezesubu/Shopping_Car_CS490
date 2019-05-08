-- # CREATE USER 'oss'@'localhost' IDENTIFIED BY 'oss';
-- # GRANT ALL PRIVILEGES ON OSS.* TO 'oss'@'localhost';
-- # GRANT ALL PRIVILEGES ON mock_bank_db.* TO 'oss'@'localhost';
-- # FLUSH PRIVILEGES;

INSERT INTO user VALUES (1 , 'masterus2018@gmail.com', '$2a$10$/SyKSvFEd563XcgyLDA7reJsH91YdDhlXqGCRGe7nzOqRN1Pfu9Cy', 'ENABLED','Wenxin');
INSERT INTO user VALUES (3 , 'ezesubu@gmail.com', '$2a$10$/SyKSvFEd563XcgyLDA7reJsH91YdDhlXqGCRGe7nzOqRN1Pfu9Cy', 'ENABLED', 'Eazy');
INSERT INTO user VALUES (4 , 'katebasoft@gmail.com', '$2a$10$/SyKSvFEd563XcgyLDA7reJsH91YdDhlXqGCRGe7nzOqRN1Pfu9Cy', 'ENABLED','Innocent');
INSERT INTO user VALUES (5 , 'shulinghe51@gmail.com', '$2a$10$/SyKSvFEd563XcgyLDA7reJsH91YdDhlXqGCRGe7nzOqRN1Pfu9Cy', 'ENABLED','Shuling');
INSERT INTO user VALUES (6 , 'ttibebu@mum.edu', '$2a$10$/SyKSvFEd563XcgyLDA7reJsH91YdDhlXqGCRGe7nzOqRN1Pfu9Cy', 'ENABLED','Thomas');
INSERT INTO user VALUES (7 , 'lyngoctram1709@gmail.com', '$2a$10$/SyKSvFEd563XcgyLDA7reJsH91YdDhlXqGCRGe7nzOqRN1Pfu9Cy', 'ENABLED','Tram');
INSERT INTO user VALUES (14, 'rick@hotmail.com','$2a$10$/SyKSvFEd563XcgyLDA7reJsH91YdDhlXqGCRGe7nzOqRN1Pfu9Cy', 'ENABLED', 'rick');
INSERT INTO admin VALUES ('Wenxin', 'He', 1);


INSERT INTO customer VALUES ('customer1', 'cu1', 3);
INSERT INTO user VALUES (8, 'customer1@mum.edu','$2a$10$/SyKSvFEd563XcgyLDA7reJsH91YdDhlXqGCRGe7nzOqRN1Pfu9Cy', 'ENABLED', 'costumer1');
INSERT INTO customer VALUES ('customer2', 'cu2', 8);
INSERT INTO user VALUES (9, 'customer2@gmail.com','$2a$10$/SyKSvFEd563XcgyLDA7reJsH91YdDhlXqGCRGe7nzOqRN1Pfu9Cy', 'ENABLED', 'customer2');
INSERT INTO customer VALUES ('customer3', 'cu3', 9);
INSERT INTO user VALUES (10, 'tbatmunkh@mum.edu','$2a$10$/SyKSvFEd563XcgyLDA7reJsH91YdDhlXqGCRGe7nzOqRN1Pfu9Cy', 'ENABLED', 'customer3');
INSERT INTO customer VALUES ('customer4', 'cu4', 10);
INSERT INTO user VALUES (11, 'customer4@gmail.com','$2a$10$/SyKSvFEd563XcgyLDA7reJsH91YdDhlXqGCRGe7nzOqRN1Pfu9Cy', 'ENABLED', 'customer4');
INSERT INTO customer VALUES ('customer5', 'cu5', 11);
INSERT INTO user VALUES (12, 'customer5@mum.edu','$2a$10$/SyKSvFEd563XcgyLDA7reJsH91YdDhlXqGCRGe7nzOqRN1Pfu9Cy', 'ENABLED', 'customer5');
INSERT INTO customer VALUES ('customer6', 'cu6', 12);
INSERT INTO user VALUES (13, 'customer7@gmail.com','$2a$10$/SyKSvFEd563XcgyLDA7reJsH91YdDhlXqGCRGe7nzOqRN1Pfu9Cy', 'ENABLED', 'customer6');
INSERT INTO customer VALUES ('customer7', 'cu7', 13);
INSERT INTO user VALUES (2 , 'vendor@gmail.com', '$2a$10$/SyKSvFEd563XcgyLDA7reJsH91YdDhlXqGCRGe7nzOqRN1Pfu9Cy', 'ENABLED', 'vendor');

INSERT INTO vendor VALUES ('Nike', 'vendor\\4\\4.png', 4);
INSERT INTO vendor VALUES ('Louis Vuitton', 'vendor\\5\\5.png', 5);
INSERT INTO vendor VALUES ('Rolex', 'vendor\\6\\6.png', 6);
INSERT INTO vendor VALUES ('Oakley', 'vendor\\7\\7.jpg', 7);
INSERT INTO vendor VALUES ('Tesla', 'vendor\\14\\14.png', 14);

INSERT INTO category VALUES (1, 'category\\1\\1.jpg', "Women",'ENABLED',null);
INSERT INTO category VALUES (2, 'category\\2\\2.jpg', "Men",'ENABLED',null);
INSERT INTO category VALUES (3, 'category\\3\\3.jpg', "Clothing",'ENABLED',1);
INSERT INTO category VALUES (4, 'category\\4\\4.jpg', "Bag",'ENABLED',1);
INSERT INTO category VALUES (7, 'category\\7\\7.jpg', "Accessories",'ENABLED',2);
INSERT INTO category VALUES (6, 'category\\6\\6.jpg', "Shoes",'ENABLED', 2);
INSERT INTO category VALUES (5, 'category\\5\\5.jpg', "Clothing",'ENABLED',7);
INSERT INTO category VALUES (8, 'category\\8\\8.jpg', "Cars", 'ENABLED', null);
INSERT INTO category VALUES (9, 'category\\9\\9.jpg', "Sunglasses", 'ENABLED', 7);


INSERT INTO card_detail VALUES(1, 'QraOsmY1Vm/D/pQN6BuLhw==', 'hiE+wqyvnTqJkKbp/OsjdA==', 'wCs2kVuyP/Wl1r1z25CSLvgzFwyz9ICwGILHUe1Ku8c=', 'VISA', 'PDxRRthWgxfXXzrhUr4D1w==', '1000', 'ENABLED', 'PM0IK0uDDL5hne6fEhSiQg==', NULL, 1);
INSERT INTO card_detail VALUES(2, 'QraOsmY1Vm/D/pQN6BuLhw==', 'SCyf4mtAPdUbXAUeuJXTZg==', 'ZsI1f6td8VkuQmP2o7zrsvgzFwyz9ICwGILHUe1Ku8c=', 'VISA', 'E+xQ7+d1sHpp+x5e9vK7UA==', '2000', 'ENABLED', 'VV8Po+pw3CYRaB151M9MdQ==', NULL, NULL);
INSERT INTO card_detail VALUES(3, 'QraOsmY1Vm/D/pQN6BuLhw==', 'U20GoH1QCTVt89PhV9iiBQ==', 'CyIxvjmUDvX8d3MAODdv+PgzFwyz9ICwGILHUe1Ku8c=', 'VISA', 'iU6EhOP10yd+6m1+7hxHgg==', '3001', 'ENABLED', 'PM0IK0uDDL5hne6fEhSiQg==', NULL, 2);
INSERT INTO card_detail VALUES(4, 'QraOsmY1Vm/D/pQN6BuLhw==', 'ZJENHTyNg0q7K2Nj4j5E0g==', 'xljjdxpBdvEfFMGJtIhCx/gzFwyz9ICwGILHUe1Ku8c=', 'VISA', 'qut4mUwppXC4xP3Sbj782w==', '3002', 'ENABLED', 'PM0IK0uDDL5hne6fEhSiQg==', NULL, 4);
INSERT INTO card_detail VALUES(5, 'QraOsmY1Vm/D/pQN6BuLhw==', 'BaLQhxhQS+4sOa3QYkMUBQ==', 'sLmmlQugNFEJyAIbjiRyoPgzFwyz9ICwGILHUe1Ku8c=', 'VISA', 'Zr4BWk3jr48H6wzDWqQczA==', '3003', 'ENABLED', 'PM0IK0uDDL5hne6fEhSiQg==', NULL, 5);
INSERT INTO card_detail VALUES(6, 'QraOsmY1Vm/D/pQN6BuLhw==', '4wkdjUWqjez+bE9w0Jm7bA==', 'ZQrhYoCB5yrJW0uUEwajn/gzFwyz9ICwGILHUe1Ku8c=', 'VISA', 'DNC/nbfdZRgR64YMwhuPlw==', '3004', 'ENABLED', 'PM0IK0uDDL5hne6fEhSiQg==', NULL, 6);
INSERT INTO card_detail VALUES(7, 'QraOsmY1Vm/D/pQN6BuLhw==', 'McuMaay2jbl+KVxe/IXqFg==', 'J78CUbF9WYpMKC9ZCedOOPgzFwyz9ICwGILHUe1Ku8c=', 'VISA', '37vF6TCpGI55juND9qv8XA==', '3005', 'ENABLED', 'PM0IK0uDDL5hne6fEhSiQg==', NULL, 7);
INSERT INTO card_detail VALUES(8, '6zoQ5Q3P0FkN7cMvQQdYtA==', '5wM9IyibGkIBralK1Lb6vA==', 'gMJ7svtpR5YGu5UiYXg0G/gzFwyz9ICwGILHUe1Ku8c=', 'VISA', 'AIZ9VtyuXwhp/Wj+Cz4yug==', '3699', 'ENABLED', 'PM0IK0uDDL5hne6fEhSiQg==', NULL, 3);

INSERT INTO card_detail VALUES(9, 'QraOsmY1Vm/D/pQN6BuLhw==', 'hiE+wqyvnTqJkKbp/OsjdA==', 'wCs2kVuyP/Wl1r1z25CSLvgzFwyz9ICwGILHUe1Ku8c=', 'VISA', 'PDxRRthWgxfXXzrhUr4D1w==', '1000', 'ENABLED', 'PM0IK0uDDL5hne6fEhSiQg==', NULL, 3);
INSERT INTO address VALUES (1, 'Fairfield', '2058871599', 'Iowa', 'ENABLED', '1000 N 4th St', '52557', 3);
INSERT INTO address VALUES (2, 'Fairfield', '2058871599', 'Iowa', 'ENABLED', '52 E. Golden Lane', '52556', 3);

INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (1, 'White Checker bag, elegant yet gorgeous', 'product\\1\\1.jpg', 'Louis Vuitton White Checker bag', 900, 3, 'ENABLED', 4, 5);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (2, 'Branded Mechanical Watch by ROLEX', 'product\\2\\2.jpg', 'ROLEX VISION', 5000, 4, 'ENABLED', 5, 6);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (3, 'Nike Adapt BB Black Pure Platinum (US Charger)', 'product\\3\\3.jpg', 'Nike Adapt BB', 1200, 5, 'ENABLED', 6, 4);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (4, 'Louis Vuitton Most Luxurious Bag ever made', 'product\\4\\4.jpg', 'Louis Vuitton Black Leather', 1169, 8, 'ENABLED', 4, 5);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (5, 'Pink Clothing', 'product\\5\\5.jpg', 'Pink Clothing', 60, 10, 'ENABLED', 3, 2);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (6, 'Prepare to amazed by this beautiful brown bag', 'product\\6\\6.jpg', 'Louis Vuitton Brown Leather bag', 800, 3, 'ENABLED', 4, 5);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (7, 'Brown Checker bag, elegant yet gorgeous', 'product\\7\\7.png', 'Louis Vuitton brown Checker bag', 1349, 3, 'ENABLED', 4, 5);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (8, 'Nike Air Force 1', 'product\\8\\8.jpg', 'Nike Air Force 1', 780, 5, 'ENABLED', 6, 4);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (9, 'Kyrie 5', 'product\\9\\9.jpg', 'Kyrie 5 By You Basketball Shoe.', 3598, 5, 'ENABLED', 6, 4);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (10, 'Nike Running Shoes', 'product\\10\\10.jpg', 'Nike Grey Running Shoes', 1898, 5, 'ENABLED', 6, 4);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (11, 'Nike Air Max 270 Shoes', 'product\\11\\11.jpg', 'Air Max 270 Shoes', 2778, 5, 'ENABLED', 6, 4);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (12, 'Gold Plated Branded Mechanical Watch by ROLEX', 'product\\12\\12.jpg', 'ROLEX Peace', 7334, 3, 'ENABLED', 5, 6);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (13, 'High Tech looking sunglasses for all handsome males', 'product\\13\\13.jpg', 'Oakley Flamer', 450, 9, 'ENABLED', 9, 7);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (14, 'Historical sunglasses for tough Man', 'product\\14\\14.jpg', 'Oakley Historic', 533, 8, 'ENABLED', 9, 7);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (15, 'Sunglasses for every working adults', 'product\\15\\15.jpg', 'Oakley Palmer', 330, 12, 'ENABLED', 9, 7);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (16, 'Futuristic embodiment of sunglasses', 'product\\16\\16.jpg', 'Oakley Hunger', 435, 9, 'ENABLED', 9, 7);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (17, 'Cheapest Tesla Electric car in the market.', 'product\\17\\17.jpg', 'Tesla Model 3', 15000, 5, 'ENABLED', 8, 14);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (18, 'Electric Tesla SUV with the greatest price reduction ever', 'product\\18\\18.jpg', 'Tesla Model X', 17000, 4, 'ENABLED', 8, 14);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (19, 'Waist-Defined Sleeveless Keyhole Romper for Women', 'product/19/19.jpg', 'Waist-Defined Sleeveless Keyhole', 60, 20, 'ENABLED', 3, 4);
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `status`, `category_id`, `vendor_id`) VALUES (20, 'Modest Islamic Women ', 'product/20/20.png', 'Clothing dress', 500, 2, 'ENABLED', 3, 4);

INSERT INTO order_detail VALUES(1, 150, 3, 1, 1);
INSERT INTO `order` VALUES (1, DATE '2018-4-4', DATE '2018-4-4', 'ENABLED', 1, 1, 10, NULL);
INSERT INTO order_detail VALUES(2, 160, 3, 2, 2);
INSERT INTO `order` VALUES (2, DATE '2018-4-10', DATE '2018-4-10', 'ENABLED', 1, 2, 10, NULL);
INSERT INTO order_detail VALUES(3, 170, 3, 3, 3);
INSERT INTO `order` VALUES (3, DATE '2018-4-15', DATE '2018-4-15', 'ENABLED', 1, 3, 10, NULL);
INSERT INTO order_detail VALUES(4, 180, 3, 4, 4);
INSERT INTO `order` VALUES (4, DATE '2018-5-4', DATE '2018-5-4', 'ENABLED', 1, 4, 10, NULL);
INSERT INTO order_detail VALUES(5, 190, 3, 5, 5);
INSERT INTO `order` VALUES (5, DATE '2018-5-4', DATE '2018-5-4', 'ENABLED', 1, 4, 11, NULL);
INSERT INTO order_detail VALUES(6, 201, 3, 6, 4);
INSERT INTO `order` VALUES (6, DATE '2018-5-4', DATE '2018-5-4', 'ENABLED', 1, 4, 11, NULL);
