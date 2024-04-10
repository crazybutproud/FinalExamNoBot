-- alter table countries_info
--     add column profile_id int
--             references profiles(id);


alter table profiles
    add column user_id int
            references users(id)