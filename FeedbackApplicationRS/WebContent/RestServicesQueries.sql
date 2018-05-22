
Create table FEEDBACK_TABLE(
FeedbackNumber		number(5)			not null,
Name				varchar2(20)		not null,
Comments			varchar2(400),
Star				number(2)			not null
)
drop table FEEDBACK_TABLE

insert into feedback_table values(1, 'Deepak Sharma', 'good try', 5)

create sequence FeedbackSequence
start with 1
increment by 1
nocache nocycle

drop sequence FeedbackSequence

select currvalue from FeedbackSequence
select FeedbackSequence.nextval from dual
select * from Feedback_table