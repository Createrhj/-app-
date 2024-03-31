create
    definer = root@localhost procedure addOrders(IN userID int, IN ordersID bigint, OUT result int)
BEGIN
	-- 定义错误码
	DECLARE error INTEGER DEFAULT 0;
	-- 当出现异常时错误码赋1
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error=1;
	-- 开启事务
	start TRANSACTION;

	insert into orders_items(goods_id, goods_number, goods_total)
	SELECT cart.goods_id,goods_number,goods_number*goods_price from cart,goods
	where cart.goods_id=goods.goods_id
    and user_id=userID and is_check=1;

	update orders_items set orders_id=ordersID where orders_id IS NULL;
	delete from cart where user_id=userID and is_check=1;
	-- 如果出现异常回滚事务，否则提交事务
	IF error = 1 THEN
		ROLLBACK;
		set result = 1;
	ELSE
        COMMIT;
		set result = 0;
    END IF;
	END;

