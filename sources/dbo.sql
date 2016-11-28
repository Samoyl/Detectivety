DROP TABLE ImageTable
GO
CREATE TABLE ImageTable (
Id int NOT NULL IDENTITY(1,1) PRIMARY KEY,
Link nvarchar(MAX) NOT NULL ,
Image image NULL 
)

