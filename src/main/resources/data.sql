insert into author(id, name, email) values('b8addf5e-43f8-4165-9a5f-ab73094a5b1e', 'peter', 'peteer@gmail.com');
insert into author(id, name, email) values('bffc75ba-c8e0-4178-93c9-20eb6cafed75', 'Smith', 'Smith@gmail.com');
insert into author(id, name, email) values('44b0ed8f-351c-4368-a472-cadd17444947', 'John', 'John@gmail.com');
insert into author(id, name, email) values('023946ee-6173-4d49-8bac-c0b9e4a7ba88', 'Shane', 'Shane@gmail.com');


insert into post(id, title, description, category, author_id) values('8144f608-3e87-47ea-ad16-677d5679b445', 'post title','post text', 'Adventure', 'b8addf5e-43f8-4165-9a5f-ab73094a5b1e');
insert into post(id, title, description, category, author_id) values('883b8a41-831f-4a1a-86ca-d26adc8f84aa', 'spring boot','spring boot and graphQl', 'IT', 'bffc75ba-c8e0-4178-93c9-20eb6cafed75');
insert into post(id, title, description, category, author_id) values('6c80180c-51de-4f56-81c4-da0a8fbfa792', 'REACT','REACT course', 'IT', '44b0ed8f-351c-4368-a472-cadd17444947');
insert into post(id, title, description, category, author_id) values('7e759e91-a0d4-469b-ad81-50ba3969d211', 'JS','JS course', 'IT', '44b0ed8f-351c-4368-a472-cadd17444947');

insert into comment(id, text, author_id, post_id) values ('880dc5c4-ca56-4eef-90e8-fd6703639ecf', 'awesome comment', '44b0ed8f-351c-4368-a472-cadd17444947', '6c80180c-51de-4f56-81c4-da0a8fbfa792');