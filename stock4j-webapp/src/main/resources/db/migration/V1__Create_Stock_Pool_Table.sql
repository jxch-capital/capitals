create table public.stock_pool
(
    id     bigint generated by default as identity
        primary key,
    codes  text,
    engine varchar(20)  not null,
    name   varchar(255) not null,
    userid varchar(50)  not null
);

comment on table public.stock_pool is '股票池';

comment on column public.stock_pool.codes is '股票代码，逗号区分';

comment on column public.stock_pool.engine is '股票引擎';

comment on column public.stock_pool.name is '股票池名称';

comment on column public.stock_pool.userid is '用户ID';

alter table public.stock_pool
    owner to capitals;

create unique index stock_pool_index_userid_name
    on public.stock_pool (userid, name);

