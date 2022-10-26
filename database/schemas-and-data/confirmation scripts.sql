use wing_span_test;

select au.app_user_id, ar.app_role_id, `name`, username, password_hash, enabled, email, user_first_name, user_last_name, a.avatar_id, avatar_description, avatar_img_url, b.badge_id, badge_name, badge_description, badge_img_url from app_user_role aur
	inner join app_role ar on aur.app_role_id = ar.app_role_id
    inner join app_user au on aur.app_user_id = au.app_user_id
    inner join user_avatar ua on au.app_user_id = ua.app_user_id
    inner join user_badge ub on au.app_user_id = ub.app_user_id
    inner join avatar a on a.avatar_id = ua.avatar_id
    inner join badge b on b.badge_id = ub.badge_id;
    
select au.username, s.sighting_id, b.common_name, b.scientific_name, b.img_url, t.name, s.sighting_date, s.city, s.state, s.daytime from sighting s
	inner join app_user au on s.app_user_id = au.app_user_id
	inner join sighting_trait st on st.sighting_id = s.sighting_id
    inner join trait t on st.trait_id = t.trait_id
    inner join bird b on s.bird_id = b.bird_id
where au.app_user_id = 1;

select r.name from app_user_role ur 
	inner join app_role r on ur.app_role_id = r.app_role_id 
	inner join app_user au on ur.app_user_id = au.app_user_id 
	where au.email = 'admin@admin.com';
                
select * from app_user_role aur
	inner join app_user au on aur.app_user_id = au.app_user_id
    inner join app_role ur on aur.app_role_id = ur.app_role_id;

-- delete from sighting_trait where sighting_id = 1;
-- select * from sighting_trait;
-- delete from sighting where sighting_id = 1;

select b.bird_id, b.common_name, b.scientific_name, b.img_url from sighting s
	inner join bird b on s.bird_id = b.bird_id
where s.sighting_id = 1;

select * from sighting;

select * from bird
	where bird_id = 1;

select * from app_user;

select * from app_user_role;