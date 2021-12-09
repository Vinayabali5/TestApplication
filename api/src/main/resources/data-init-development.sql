-----------------------------------------
------------ Initial Data ---------------

insert into onlineapplication.school (name, line1, urn) values ('Oakwood', 'Some Place', 10016805), ('De Stafford', 'Some Street', 10061802), ('Reigate School', 'Some Avenue', 10066018), ('The Warwick', 'Some Road', 10062358);

insert into onlineapplication.contact_type (code, description) values ('M', 'Mother'), ('F', 'Father'), ('G', 'Guardian'), ('O', 'Other');

insert into onlineapplication.title (code, description, allow_student) values ('1','Mr', 1), ('2','Mrs', 0), ('3','Miss', 1), ('4','Ms', 0), ('5','Dr', 0), ('6','Rev', 0), ('7','Sir', 0), ('8','Lady', 0), ('9','Lord', 0), ('10','Dame', 0), ('11','Prof', 0), ('12','Capt', 0), ('98','None', 0), ('99','Other', 0);
insert into onlineapplication.legal_sex (code, description) values ('M', 'Male'), ('F', 'Female');
insert into onlineapplication.gender (code, description) values ('M', 'Male'), ('F', 'Female'), ('O','Other');

insert into onlineapplication.nationality (code, description) VALUES ('UK', 'United Kingdon'), ('EU', 'European Union'), ('O', 'Other');

insert into onlineapplication.course (title, summary, entry_requirements, external_link) values
('A Level Chemistry', 'The course places a strong emphasis on the ways in which Chemistry is used. Students study Chemistry in a variety of contexts - chemistry of the atmosphere, fuel technology, medicines etc. Practical skills are embedded throughout the course.', 'GCSE Science at Grade 4', 'http://www.reigate.ac.uk'),
('A Level Biology', 'Year 1: Topics 1 - 4 covering basic cardiovascular biology, cellular biology (animal & plant), cell division (meiosis, mitosis & stem cells), cell membrane structure and solute transport, biochemistry (carbohydrates, lipids, DNA), molecular biology (DNA replication, protein synthesis, epigenetics), natural selection & evolution, biodiversity and conservation. Year 2: Topics 5 - 8 covering photosynthesis and energy flow in ecosystems, climate change and speciation, forensic biology, immunology and human health and disease, respiration, muscle contraction, advanced cardiovascular biology, the nervous system, the brain, learning and memory, animal ethics.', 'GCSE Science at Grade 4', 'http://www.reigate.ac.uk'),
('A Level Mathematics', 'Students will study 67% Pure Mathematics and 33% Applied Mathematics (Statistics and Mechanics). An emphasis is placed on problem solving and mathematical communication throughout the course. Students will require a scientific calculator which is suitable for the new A’ Level Specification, costing approximately £25.', 'GCSE Maths at Grade 4', 'http://www.reigate.ac.uk');

insert into onlineapplication.[user] (user_id, username, password, confirmation_code, confirmed) values
(1, 'test@reigate.ac.uk', '$2a$10$dG.MT8F7FKz/cOBd2CCAG.9fQ5KFkCno0eL54uTg.Vy4ayG.E2wwq', '3e9c2e7c22644ca987ea0a32d01d6a11', 0),
(2, 'test2@reigate.ac.uk', '$2a$10$dG.MT8F7FKz/cOBd2CCAG.9fQ5KFkCno0eL54uTg.Vy4ayG.E2wwq', '3e9c2e7c22644ca987ea0a32d01d6a12', 1);
