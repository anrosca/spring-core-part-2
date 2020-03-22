create sequence report_id_sequence;

create table report (
    id bigint primary key default nextval('report_id_sequence'),
    creation_timestamp timestamp not null,
    content bytea not null,
    file_name varchar(50) not null
);
