CREATE DATABASE IF NOT EXISTS springsecurity;
USE springdata;
-- 插入用户数据
INSERT INTO `user` (`username`, `password`)
VALUES ('admin', '$2a$10$2K4MZmWntNHFl2gjlDE3fOeOm1E79CP0L9ha1/GB9bd4NP/JZ4S0G'),
       ('zhangsan', '$2a$10$HClYCd.U56xoTHVKef6xm.II2.LOsOUSr.FpeLZJxExoHnlp1Vg5y'),
       ('lisi', '$2a$10$HClYCd.U56xoTHVKef6xm.II2.LOsOUSr.FpeLZJxExoHnlp1Vg5y');

-- 插入角色数据（使用INSERT IGNORE避免重复）
INSERT IGNORE INTO `role` (`name`)
VALUES  ('ADMIN'),
        ('NORMAL_USER');

-- 用户角色关联（使用INSERT IGNORE和子查询）
INSERT IGNORE INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id
FROM `user` u, `role` r
WHERE
    ((u.username = 'admin' AND r.name = 'ADMIN') OR
     (u.username IN ('zhangsan', 'lisi') AND r.name = 'NORMAL_USER'))
  AND NOT EXISTS (
    SELECT 1 FROM `user_role` ur
    WHERE ur.user_id = u.id AND ur.role_id = r.id
);