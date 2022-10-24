use wing_span;

select au.app_user_id, ar.app_role_id, `name`, username, password_hash, enabled, email, user_first_name, user_last_name, a.avatar_id, avatar_description, avatar_img_url, b.badge_id, badge_name, badge_description, badge_img_url from app_user_role aur
	inner join app_role ar on aur.app_role_id = ar.app_role_id
    inner join app_user au on aur.app_user_id = au.app_user_id
    inner join user_avatar ua on au.app_user_id = ua.app_user_id
    inner join user_badge ub on au.app_user_id = ub.app_user_id
    inner join avatar a on a.avatar_id = ua.avatar_id
    inner join badge b on b.badge_id = ub.badge_id;
    
select s.sighting_id, b.common_name, b.scientific_name, b.img_url, t.name, s.sighting_date, s.city, s.state, s.daytime from sighting s
	inner join sighting_trait st on st.sighting_id = s.sighting_id
    inner join trait t on st.trait_id = t.trait_id
    inner join bird b on s.bird_id = b.bird_id
where app_user_id = 1;
    