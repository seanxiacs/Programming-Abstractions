type bool_expr =
    | Lit of string
    | Not of bool_expr
    | And of bool_expr * bool_expr
    | Or of bool_expr * bool_expr;;

let truth_table literal1 literal2 expr =
    let rec parse a abool b bbool expr = match expr with 
        | Lit lit -> if lit = a then abool else bbool
        | Not expr -> not(parse a abool b bbool expr)
        | And (expr1, expr2) -> (parse a abool b bbool expr1) && (parse a abool b bbool expr2)
        | Or (expr1, expr2) -> (parse a abool b bbool expr1) || (parse a abool b bbool expr2) in 
            [(true, true, parse literal1 true literal2 true expr); 
            (true, false, parse literal1 true literal2 false expr); 
            (false, true, parse literal1 false literal2 true expr); 
            (false, false, parse literal1 false literal2 false expr)];;