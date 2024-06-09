package com.teamsparta.task.domain.task.comment.controller

import com.teamsparta.task.domain.task.comment.dto.AddCommentRequest
import com.teamsparta.task.domain.task.comment.dto.CommentResponse
import com.teamsparta.task.domain.task.comment.dto.UpdateCommentRequest
import com.teamsparta.task.domain.task.service.TaskService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/task/{taskId}/comments")
@RestController
class CommentController(
    private val taskService: TaskService,
) {



    @GetMapping
    @Operation(summary = "Comment 모두 조회하기", description = "모든 Comment 를 조회합니다.")
    fun getCommentList(@PathVariable taskId: Long, ) : ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.getCommentList(taskId))

    }


    @PostMapping
    @Operation(summary = "Comment 작성하기", description = "댓글을 작성합니다.")
    fun addComment(
        @PathVariable taskId: Long,
        @RequestBody request: AddCommentRequest): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(taskService.addComment(taskId, request))
    }

    @PutMapping("/{commentId}")
    @Operation(summary = "Comment Id로 수정하기", description = "댓글을 수정합니다.")
    fun updateComment(
        @PathVariable taskId: Long,
        @PathVariable commentId: Long,
        @RequestBody request: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.updateComment(taskId, commentId, request))
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Comment Id로 삭제하기", description = "댓글을 삭제합니다..")
    fun removeComment(
        @PathVariable taskId: Long,
        @PathVariable commentId: Long): ResponseEntity<Unit> {
        taskService.removeComment(taskId, commentId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}
