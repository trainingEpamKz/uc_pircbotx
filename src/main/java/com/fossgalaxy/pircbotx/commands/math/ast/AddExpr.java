package com.fossgalaxy.pircbotx.commands.math.ast;

/**
 * Copyright leon
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author leon on 16-1-1
 */
public class AddExpr extends Expr {
    private final Expr left;
    private final Expr right;

    public AddExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    public double eval() {
        return left.eval() + right.eval();
    }

    @Override
    public String toString() {
        return "AddExpr{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
