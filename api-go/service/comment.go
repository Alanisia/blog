package service

import "blog/db"

func CommentInsert() {}

func CommentDelete(id int64) {
  comment := db.CommentSearch(id)
  if comment.CommentId == 0 {
    // search for all of comment which comment_id = comment.Id
    // then remove them
  } else {
    db.CommentDelete(comment)
  }
}

func CommentList(blogId int64) {}

func ReplyList(commentId int64) {

}
