package db

import "blog/model"

func CommentInsert(comment *model.Comment) {
	if _, err := Db.Insert(comment); err != nil {
	}
}

func CommentDelete(comment *model.Comment) {
	if _, err := Db.ID(comment.Id).Delete(comment); err != nil {
	}
}

func CommentList(commentId int64) []*model.Comment {
    Db.Where("comment_id=", commentId).Find()
}

func CommentSearch(id int64) *model.Comment {
	return Db.ID(id).Get()
}
